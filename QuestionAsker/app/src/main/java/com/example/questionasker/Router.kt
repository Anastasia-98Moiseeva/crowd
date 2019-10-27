package com.example.questionasker

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.questionasker.fragments.message
import java.lang.ref.WeakReference

class Router(activity: FragmentActivity, container: Int) {
    private var num_stack = 0
    private val weakActivity = WeakReference(activity)
    private val fragmentContainer = container
    private var id_window = 0

    fun navigateTo(addToBack : Boolean = true, fragmentNew: () -> Fragment,
                   /*needAnimation : Boolean = false, */changeStack : Int = 0,
                   transportedMessage: Any? = null) {
        val activity = weakActivity.get()

        activity?.run {
            if (changeStack != 0) {
                var ch = changeStack
                while (ch > 0){
                    supportFragmentManager.popBackStack()
                    ch--
                }
            }

            val fragment = fragmentNew()
            if (transportedMessage != null) {
                val args = Bundle()
                if (transportedMessage is Int) {
                    args.putInt(message, transportedMessage)
                }
                if (transportedMessage is String) {
                    args.putString(message, transportedMessage)
                }
                fragment.arguments = args
            }

            val transaction = supportFragmentManager.beginTransaction()
            /*if (needAnimation) {
                transaction.setCustomAnimations(R.anim.anim_second, R.anim.anim_first)
            }*/
            transaction.replace(fragmentContainer, fragment)
            if (addToBack) {
                transaction.addToBackStack(fragment::class.java.simpleName)
                num_stack ++
            }
            transaction.commit()
        }
    }


    fun navigateBack() : Boolean {
        val activity = weakActivity.get()

        activity?.run {
            if (id_window != 0) {
                activity.finish()
            }
            if (supportFragmentManager.backStackEntryCount > 0 && num_stack > 0) {
                num_stack --

                supportFragmentManager.popBackStack()
                return true
            }
        }

        return false
    }
}
