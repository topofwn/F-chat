package com.luan.fchat.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.luan.fchat.R;
import com.luan.fchat.ui.dialog.NotifyConnectionDialog;
import com.luan.fchat.ui.dialog.NotifyDialog;
import com.luan.fchat.utils.AlertType;
import com.luan.fchat.utils.MessageType;
import com.luan.fchat.utils.StringUtils;
import com.luan.fchat.utils.SystemUtils;
import com.luan.fchat.utils.UIUtils;


public abstract class BaseActivity extends AppCompatActivity
        implements MVPView, BaseFragment.Callback, NotifyConnectionDialog.NotifyConnectionDialogListener {

    private ProgressDialog mProgressDialog;
    private boolean isLife = false;
    // this boolean is use for trigger function auto hide keyboard when tap out of focus edit text
    private boolean mTurnOnTouchHideKeyBoard = false;
    private NotifyConnectionDialog notifyConnectionDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLife = true;
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(BaseActivity.this);
            mProgressDialog.setTitle("Loading...");
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public boolean isActivityStillLive() {
        return isLife;
    }

    @Override
    public void finish() {
        isLife = false;
        super.finish();
    }

    @Override
    public void hideLoading() {
        if (isActivityStillLive()) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showMessage(message, MessageType.ERROR, AlertType.TOAST);
        }
    }

    @Override
    public void onError(int resId) {
        onError(StringUtils.getStringBaseOnLanguage(getApplicationContext(), resId));
    }

    @Override
    public void showMessage(int res, MessageType messageType, AlertType alertType) {
        showMessage(StringUtils.getStringBaseOnLanguage(getApplicationContext(), res),
                messageType, alertType);
    }

    @Override
    public void showMessage(String message, MessageType messageType, AlertType alertType) {
        if (message != null) {
            switch (alertType) {
                case INFO_DIALOG:
                    //  8/15/2017 show dialog here
                    NotifyDialog notifyDialog;
                    switch (messageType) {
                        case ERROR:
                            notifyDialog = new NotifyDialog(BaseActivity.this,
                                    StringUtils.getStringBaseOnLanguage(getApplicationContext(), R.string.some_error), message);
                            break;
                        default:
                            notifyDialog = new NotifyDialog(BaseActivity.this,
                                    StringUtils.getStringBaseOnLanguage(getApplicationContext(), R.string.congrats), message);
                            break;
                    }
                    notifyDialog.show();
                    break;
                case TOAST:
                    UIUtils.showToast(this, message);
                    break;
                case SNACKBAR:
                    break;
            }
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return SystemUtils.isConnectingToInternet(BaseActivity.this);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        }
    }

    public void turnOnTouchHideKeyBoard() {
        mTurnOnTouchHideKeyBoard = true;
    }

    @Override
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        // all touch events close the keyboard before they are processed except
        // EditText instances.
        // if focus is an EditText we need to check, if the touchevent was
        // inside the focus editTexts
        if (!mTurnOnTouchHideKeyBoard) {
            return super.dispatchTouchEvent(ev);
        }
        final View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            if (!(currentFocus instanceof EditText) || !isTouchInsideView(ev, currentFocus)) {
                InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null)
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            }
//            if (currentFocus instanceof EditText) {
//                ((EditText) currentFocus).setCursorVisible(false);
//            }
            return super.dispatchTouchEvent(ev);
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    /**
     * determine if the given motionevent is inside the given view.
     *
     * @param ev           the given view
     * @param currentFocus the motion event.
     * @return if the given motionevent is inside the given view
     */
    private boolean isTouchInsideView(final MotionEvent ev, final View currentFocus) {
        final int[] loc = new int[2];
        currentFocus.getLocationOnScreen(loc);
        return ev.getRawX() > loc[0] && ev.getRawY() > loc[1] && ev.getRawX() < (loc[0] + currentFocus.getWidth())
                && ev.getRawY() < (loc[1] + currentFocus.getHeight());
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onTokenExpired() {
        //TODO: Add Activity to handle Expired token
//        ActivityUtils.startActivity(BaseActivity.this, WelcomeActivity.class, true);
    }

    @Override
    public void onBackPressed() {
//        FragmentManager fm = getSupportFragmentManager();
//        if (fm.getFragments() != null && !fm.getFragments().isEmpty()) {
////            fm.popBackStack();
//        } else {
//            super.onBackPressed();
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//        }
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onConnectToServerError() {
        if (notifyConnectionDialog == null) {
            notifyConnectionDialog = new NotifyConnectionDialog(BaseActivity.this,
                    StringUtils.getStringBaseOnLanguage(getApplicationContext(), R.string.api_default_error), "");
            notifyConnectionDialog.setDialogListener(this);
        } else {
            notifyConnectionDialog.setTitle(StringUtils.getStringBaseOnLanguage(getApplicationContext(), R.string.api_default_error));
        }
        if (!notifyConnectionDialog.isShowing()) {
            notifyConnectionDialog.show();
        }
    }

    @Override
    public void onDismissNotifyConnectionDialog() {
        //do nothing
    }

    @Override
    public void onNoInternetConnection() {
        if (notifyConnectionDialog == null) {
            notifyConnectionDialog = new NotifyConnectionDialog(BaseActivity.this,
                    StringUtils.getStringBaseOnLanguage(getApplicationContext(), R.string.no_internet_connection), "");
            notifyConnectionDialog.setDialogListener(this);
        } else {
            notifyConnectionDialog.setTitle(StringUtils.getStringBaseOnLanguage(getApplicationContext(), R.string.no_internet_connection));
        }
        if (!notifyConnectionDialog.isShowing()) {
            notifyConnectionDialog.show();
        }
    }

}
