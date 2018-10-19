package ${pathContainComponent}.model${dotModelSubpackage};

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ${modelName} implements Serializable {
	
	@Expose
    @SerializedName("temp_object")
    private String temp_object;

	private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
