package ${pathContainComponent}.ui${dotSubpackage}.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import ${pathContainComponent}.data.DataManager;
import ${pathContainComponent}.utils.RxSyncList;
import ${pathContainComponent}.model${dotModelSubpackage}.${modelName};
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.support.v7.util.DiffUtil;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

<#if packageName??>
import ${packageName}.R;
</#if>


public class ${adapterName} extends RecyclerView.Adapter<${holderName}> implements RxSyncList<${modelName}> {

	private List<${modelName}> mListModels;
    private Context mContext;
	<#if makeOnClickListener>
    private ClickListener clickListener;
	</#if>

	public ${adapterName}(Context context) {
        this.mListModels = new ArrayList<>();
        this.mContext = context;
    }

	<#if makeOnClickListener>
    public ${adapterName}(Context context, List<${modelName}> initList, ClickListener clickListener) {
        this.mListModels = initList;
        this.mContext = context;
        this.clickListener = clickListener;
    }
	<#else>
	public ${adapterName}(Context context, List<${modelName}> initList) {
        this.mListModels = initList;
        this.mContext = context;
    }
	</#if>

    @Override
    public ${holderName} onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.${adapterLayoutName};
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ${holderName}(v);
    }
	
	<#if makeOnClickListener>
	@Override
    public void onBindViewHolder(${holderName} holder, int position) {
        ${modelName} model = mListModels.get(position);
        if (model != null) {
            holder.bind(mContext, model, clickListener);
        }
    }
	<#else>
	@Override
    public void onBindViewHolder(${holderName} holder, int position) {
        ${modelName} model = mListModels.get(position);
        if (model != null) {
            holder.bind(mContext, model);
        }
    }
	</#if>
	
	@Override
    public void add(${modelName} model) {
        if (!mListModels.contains(model)) {
            List<${modelName}> oldList = new ArrayList<>(mListModels);
            sortAndCallDiff(oldList);
        }
    }

    @Override
    public void add(List<${modelName}> channels) {
        List<${modelName}> oldList = new ArrayList<>(mListModels);
        mListModels.addAll(channels);
        sortAndCallDiff(oldList);
    }

    @Override
    public void set(${modelName} channel, int pos) {
        List<${modelName}> oldList = new ArrayList<>(mListModels);
        mListModels.set(pos, channel);
        sortAndCallDiff(oldList);
    }

    @Override
    public void remove(${modelName} channel) {
        List<${modelName}> oldList = new ArrayList<>(mListModels);
        mListModels.remove(channel);
        sortAndCallDiff(oldList);
    }

    @Override
    public void remove(int index) {
        List<${modelName}> oldList = new ArrayList<>(mListModels);
        mListModels.remove(index);
        sortAndCallDiff(oldList);
    }

    @Override
    public void set(List<${modelName}> channels) {
        List<${modelName}> oldList = new ArrayList<>(mListModels);
        mListModels.clear();
        mListModels.addAll(channels);
        sortAndCallDiff(oldList);
    }
	
	private void sortAndCallDiff(List<${modelName}> oldList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return oldList.size();
            }

            @Override
            public int getNewListSize() {
                return mListModels.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                ${modelName} oldData = oldList.get(oldItemPosition);
                ${modelName} newData = mListModels.get(newItemPosition);
                return oldData.getId() == (newData.getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                ${modelName} oldData = oldList.get(oldItemPosition);
                ${modelName} newData = mListModels.get(newItemPosition);
                return false;
            }
        });
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public void clear() {
        int oldSize = mListModels.size();
        mListModels.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    @Override
    public int getItemCount() {
        return mListModels.size();
    }

    @Override
    public ${modelName} getItem(int position) {
        if (position < mListModels.size()) {
            return mListModels.get(position);
        }
        return null;
    }
	
	<#if makeOnClickListener>
	public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
	</#if >
	<#if makeOnClickListener>
		public interface ClickListener {
			void onItemClicked(View v, int position);
		}
	</#if >
}
