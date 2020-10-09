package id.pahlevikun.compose.di.dependencies.autoinit

import android.content.Context
import androidx.startup.Initializer
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient
import timber.log.Timber

class FrescoInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Timber.d("FrescoInitializer is initialized.")

        val pipelineConfig =
            OkHttpImagePipelineConfigFactory
                .newBuilder(context, OkHttpClient.Builder().build())
                .setDiskCacheEnabled(true)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build()

        Fresco.initialize(context, pipelineConfig)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(TimberInitializer::class.java)
}
