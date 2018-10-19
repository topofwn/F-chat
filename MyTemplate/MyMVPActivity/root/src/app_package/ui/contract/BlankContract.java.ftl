package ${pathContainComponent}.ui${dotSubpackage};

import android.support.annotation.NonNull;
import ${pathContainComponent}.ui.base.MVPPresenter;
import ${pathContainComponent}.ui.base.MVPView;

public interface ${contractName}{

<#if includePresenter>
	interface Presenter<V extends View> extends MVPPresenter<V> {

    }
</#if>

<#if includeView>
    interface View extends MVPView {
      
    }
</#if>


}
