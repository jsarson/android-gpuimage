package jp.co.cyberagent.android.gpuimage.filter;

/**
 * a filter that inherits from [GPUImageNormalBlendFilter]
 * and apply (normal blend/paste) saved state on top of the current result
 *
 * @see GPUImageSaveCurrentStateFilter
 */
public class GPUImageNormalBlendSavedStateFilter extends GPUImageNormalBlendFilter {
    protected String tag;

    public String getTag() {
        return tag;
    }

    public GPUImageNormalBlendSavedStateFilter(String tag) {
        this.tag = tag;
    }
}
