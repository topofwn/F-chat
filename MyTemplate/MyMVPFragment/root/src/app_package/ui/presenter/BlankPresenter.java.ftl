package ${pathContainComponent}.ui${dotSubpackage};

import android.support.annotation.NonNull;
import ${pathContainComponent}.data.DataManager;
import ${pathContainComponent}.utils.SchedulerProvider;
import ${pathContainComponent}.ui.base.MVPPresenter;
import ${pathContainComponent}.ui.base.MVPView;
import ${pathContainComponent}.ui.base.BasePresenter;

public class ${presenterName}<V extends ${contractName}.View> extends BasePresenter<V> implements ${contractName}.Presenter<V> {

	public ${presenterName}(SchedulerProvider schedulerProvider, DataManager dataManager) {
        super(schedulerProvider, dataManager);
    }

    public ${presenterName}() {
        super();
    }
	
	@Override
    public void onViewInitialized() {
        super.onViewInitialized();
    }

}
