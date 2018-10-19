package ${pathContainComponent}.ui${dotSubpackage};

import android.support.annotation.NonNull;
import android.support.annotation.IdRes;
import ${pathContainComponent}.ui.base.MVPPresenter;
import ${pathContainComponent}.ui.base.MVPView;

public interface ${contractName}{

<#if includePresenter>
	interface Presenter<V extends View> extends MVPPresenter<V> {

    }
</#if>

<#if includeView>
    interface View extends MVPView {
      <T extends android.view.View> T findViewById(@IdRes int id);
    }
</#if>


}
