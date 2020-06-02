package corp.ncsoft.ncapps.submoduletest

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.lottie.LottieAnimationView
import corp.ncsoft.ncapps.submoduletest.ui.loadDefaultLottie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<AppCompatButton>(R.id.button_start).setOnClickListener {
            findViewById<LottieAnimationView>(R.id.lottie_view).apply {
                addAnimatorListener(object : LottieAnimListener() {
                    override fun onAnimationEnd(animation: Animator?) =
                        super.onAnimationEnd(animation)
                            .also { visibility = View.GONE; cancelAnimation() }
                })
                loadDefaultLottie()
            }
        }
    }
}

open class LottieAnimListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}

    override fun onAnimationEnd(animation: Animator?) {}

    override fun onAnimationCancel(animation: Animator?) {}

    override fun onAnimationStart(animation: Animator?) {}
}
