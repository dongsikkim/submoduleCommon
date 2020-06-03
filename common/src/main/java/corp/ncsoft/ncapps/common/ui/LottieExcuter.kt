package corp.ncsoft.ncapps.common.ui

import android.animation.Animator
import android.annotation.SuppressLint
import android.view.View
import androidx.annotation.IdRes
import com.airbnb.lottie.LottieAnimationView
import corp.ncsoft.ncapps.common.R

@SuppressLint("ResourceType")
fun LottieAnimationView.loadDefaultLottie(anim: LottieAnim = LottieAnim.LIKE) {
    if (isAnimating) {
        resumeAnimation()
    } else {
        progress = 0f
        visibility = View.VISIBLE
        setAnimation(anim.resId)
        playAnimation()
    }

    addAnimatorListener(object : LottieAnimListener() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            visibility = View.GONE
        }
    })
}

enum class LottieAnim(@IdRes val resId: Int) {
    LIKE(R.raw.lottie_like),
    FAVORITE(R.raw.lottie_favorite),
    UNLIKE(R.raw.lottie_unlike)
}

open class LottieAnimListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}

    override fun onAnimationEnd(animation: Animator?) {}

    override fun onAnimationCancel(animation: Animator?) {}

    override fun onAnimationStart(animation: Animator?) {}
}
