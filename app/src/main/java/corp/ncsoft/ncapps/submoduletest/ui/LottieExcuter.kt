package corp.ncsoft.ncapps.submoduletest.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.annotation.IdRes
import com.airbnb.lottie.LottieAnimationView
import corp.ncsoft.ncapps.submoduletest.R

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
}

enum class LottieAnim(@IdRes val resId: Int) {
    LIKE(R.raw.lottie_like),
    FAVORITE(R.raw.lottie_favorite),
    UNLIKE(R.raw.lottie_unlike)
}