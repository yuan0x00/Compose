package com.rapid.compose.core.common.utils

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.Insets
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * WindowInsets 工具类（适配状态栏、导航栏、键盘、刘海屏等）
 * 兼容 API 21+，使用 androidx.core.view.WindowInsetsCompat
 */
class WindowInsetsUtils private constructor() {
    init {
        throw UnsupportedOperationException("WindowInsetsUtils cannot be instantiated")
    }

    interface OnImeVisibilityChangeListener {
        fun onImeVisibilityChanged(isVisible: Boolean)
    }

    companion object {
        // —————— 自动应用系统栏 padding（最常用） ——————
        /**
         * 为 View 自动设置系统栏 padding（状态栏 + 导航栏）
         * 适用于：根布局、CoordinatorLayout、ConstraintLayout 等
         * 使用后，内容不会被系统栏遮挡
         */
        fun applySystemWindowInsets(view: View) {
            if (view is CoordinatorLayout) {
                view.setFitsSystemWindows(true)
            }
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    v!!.setPadding(
                        insets!!.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
                    )
                    insets // 不消费，允许子 View 继续处理
                })
        }

        /**
         * 为 View 设置仅顶部（状态栏）padding
         */
        fun applyTopSystemWindowInsets(view: View) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    v!!.setPadding(
                        v.getPaddingLeft(),
                        insets!!.getInsets(WindowInsetsCompat.Type.statusBars()).top,
                        v.getPaddingRight(),
                        v.getPaddingBottom()
                    )
                    insets
                })
        }

        /**
         * 为 View 设置仅底部（导航栏）padding
         */
        fun applyBottomSystemWindowInsets(view: View) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    v!!.setPadding(
                        v.getPaddingLeft(),
                        v.getPaddingTop(),
                        v.getPaddingRight(),
                        insets!!.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
                    )
                    insets
                })
        }

        // —————— 消费（consume）特定 Insets ——————
        /**
         * 消费所有系统栏 insets（子 View 不再收到）
         * 适用于：DrawerLayout、BottomSheet、全屏视频等
         */
        fun consumeSystemWindowInsets(view: View) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    // 构建消费后的 insets（系统栏设为 0）
                    val builder = WindowInsetsCompat.Builder(insets!!)
                    builder.setInsets(WindowInsetsCompat.Type.systemBars(), Insets.of(0, 0, 0, 0))
                    val consumed = builder.build()

                    // 设置 padding（可选）
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v!!.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )

                    consumed // 返回消费后的 insets，子 View 不会再收到系统栏 inset
                })
        }

        /**
         * 仅消费底部导航栏 insets
         */
        fun consumeBottomInsets(view: View) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    val builder = WindowInsetsCompat.Builder(insets!!)
                    builder.setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.of(0, 0, 0, 0)) // 消费底部导航栏
                    val consumed = builder.build()

                    val navBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                    v!!.setPadding(
                        v.getPaddingLeft(),
                        v.getPaddingTop(),
                        v.getPaddingRight(),
                        navBars.bottom
                    )
                    consumed
                })
        }

        /**
         * 仅消费顶部状态栏 insets
         */
        fun consumeTopInsets(view: View) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    val builder = WindowInsetsCompat.Builder(insets!!)
                    builder.setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(0, 0, 0, 0)) // 消费顶部状态栏
                    val consumed = builder.build()

                    val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
                    v!!.setPadding(
                        v.getPaddingLeft(),
                        statusBars.top,
                        v.getPaddingRight(),
                        v.getPaddingBottom()
                    )
                    consumed
                })
        }

        // —————— 获取当前 Insets 值（静态获取，非监听） ——————
        /**
         * 获取当前 View 的系统栏顶部 inset（状态栏高度）
         */
        fun getTopSystemInset(view: View): Int {
            val insets = ViewCompat.getRootWindowInsets(view)
            if (insets != null) {
                return insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            }
            return 0
        }

        /**
         * 获取当前 View 的系统栏底部 inset（导航栏高度）
         */
        fun getBottomSystemInset(view: View): Int {
            val insets = ViewCompat.getRootWindowInsets(view)
            if (insets != null) {
                return insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
            }
            return 0
        }

        // —————— 工具方法：移除监听器 ——————
        /**
         * 移除 View 的 WindowInsets 监听器（恢复默认行为）
         */
        fun removeOnApplyWindowInsetsListener(view: View) {
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }

        // —————— 高级：监听 Insets 变化（如键盘弹出） ——————
        /**
         * 监听 IME（键盘）弹出/隐藏
         * 
         * @param onImeVisibilityChangeListener 回调：isVisible=true 表示键盘弹出
         */
        fun addImeVisibilityListener(
            view: View,
            onImeVisibilityChangeListener: OnImeVisibilityChangeListener
        ) {
            ViewCompat.setOnApplyWindowInsetsListener(
                view,
                OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                    val imeInsets = insets!!.getInsets(WindowInsetsCompat.Type.ime())
                    val imeVisible = imeInsets.bottom > 0 || imeInsets.top > 0
                    onImeVisibilityChangeListener.onImeVisibilityChanged(imeVisible)
                    insets
                })
        }
    }
}