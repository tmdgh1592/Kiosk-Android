package com.swuniv.agefree.presentation.detection.utils

import com.swuniv.agefree.R
import com.swuniv.agefree.presentation.detection.ui.defaults.menu.Menu

object DataProvider {

    private fun getCoffeeList(): List<Menu> {
        return listOf<Menu>(
            Menu("돌체라떼", 5000, R.drawable.coffee_01),
            Menu("바닐라라떼", 5500, R.drawable.coffee_02),
            Menu("아메리카노", 4000, R.drawable.coffee_03),
            Menu("카페라떼", 4000, R.drawable.coffee_04),
            Menu("카페모카", 5000, R.drawable.coffee_05),
            Menu("캬라멜마끼야또", 6000, R.drawable.coffee_06),
        )
    }

    private fun getAdeList(): List<Menu> {
        return listOf<Menu>(
            Menu("딸기레몬에이드", 6000, R.drawable.ade_01),
            Menu("레몬에이드", 6000, R.drawable.ade_02),
            Menu("블루레몬에이드", 6000, R.drawable.ade_03),
            Menu("자몽에이드", 6500, R.drawable.ade_04),
            Menu("청포도에이드", 7000, R.drawable.ade_05),
            Menu("체리콕", 7000, R.drawable.ade_06),
        )
    }

    private fun getTeaList(): List<Menu> {
        return listOf<Menu>(
            Menu("레몬티", 5000, R.drawable.tea_01),
            Menu("밀크티", 5000, R.drawable.tea_02),
            Menu("복숭아아이스티", 2500, R.drawable.tea_03),
            Menu("얼그레이", 4000, R.drawable.tea_04),
            Menu("자스민", 4000, R.drawable.tea_05),
            Menu("캐모마일", 4000, R.drawable.tea_06),
        )
    }

    private fun getJuiceList(): List<Menu> {
        return listOf<Menu>(
            Menu("딸기주스", 5000, R.drawable.juice_01),
            Menu("망고주스", 5500, R.drawable.juice_02),
            Menu("바나나주스", 4000, R.drawable.juice_03),
            Menu("수박주스", 4000, R.drawable.juice_04)
        )
    }

    private fun getCakeList(): List<Menu> {
        return listOf<Menu>(
            Menu("바스크치즈", 6000, R.drawable.cake_01),
            Menu("생크림카스테라", 6000, R.drawable.cake_02),
            Menu("쇼콜라갸또", 6000, R.drawable.cake_03),
        )
    }

}