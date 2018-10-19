package ${pathContainComponent}.ui${dotSubpackage}.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import ${pathContainComponent}.model${dotModelSubpackage}.${modelName};

<#if packageName??>
import ${packageName}.R;
</#if>

public class ${holderName} extends RecyclerView.ViewHolder implements View.OnClickListener{
	
	<#if makeOnClickListener>
    private ${adapterName}.ClickListener clickListener;
	</#if>

    public ${holderName}(View itemView) {
        super(itemView);
        //tempView = itemView.findViewById(R.id.temp_view);       
        //tempView.setOnClickListener(this);
    }

	<#if makeOnClickListener>
    public void bind(Context context, @NonNull ${modelName} model, ${adapterName}.ClickListener clickListener) {
        this.clickListener = clickListener;       
    }
	<#else>
	public void bind(Context context, @NonNull ${modelName} model) {
       
    }
	</#if>

	
    @Override
    public void onClick(View view) {
	<#if makeOnClickListener>
        if (clickListener != null) {
            clickListener.onItemClicked(view, getAdapterPosition());
        }
	</#if>
    }
	

}
