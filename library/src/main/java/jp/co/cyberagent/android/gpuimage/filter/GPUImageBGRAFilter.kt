package jp.co.cyberagent.android.gpuimage.filter

/**
 * simple filter to change image space format from whatever can be decoded to BGRA color space
 */
class GPUImageBGRAFilter : GPUImageFilter(NO_FILTER_VERTEX_SHADER, BGRA_FRAGMENT_SHADER) {
    companion object {
        const val BGRA_FRAGMENT_SHADER = """ 
            varying highp vec2 textureCoordinate;
            uniform sampler2D inputImageTexture;
            
            void main()
            {
                lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);
                gl_FragColor = vec4(textureColor.b, textureColor.g, textureColor.r, textureColor.a);
            }
            """
    }
}