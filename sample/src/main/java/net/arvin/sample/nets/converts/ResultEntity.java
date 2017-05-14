package net.arvin.sample.nets.converts;

import com.google.gson.JsonElement;

/**
 * Created by arvinljw on 17/5/14 23:58
 * Function：
 * Desc：
 */
public class ResultEntity {
    private boolean error;
    private JsonElement results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public JsonElement getResults() {
        return results;
    }

    public void setResults(JsonElement results) {
        this.results = results;
    }
}
