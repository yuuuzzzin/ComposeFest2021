package com.example.mybasicscodelab

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

/**
 * layout 수정자를 사용하면 람다 매개변수 두 개를 얻습니다.
 * - measurable: 측정하고 배치할 하위 요소
 * - constraints: 하위 요소의 너비와 높이 최솟값과 최댓값
 */

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->

        // 컴포저블 측정 -> 측정된 width, height 가짐
        val placeable = measurable.measure(constraints)

        // 컴포저블이 first baseline을 가지고 있는지 확인 (없을 경우 Error)
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        // 컴포저블의 Top 부터 FirstBaseline 까지 측정
        val firstBaseline = placeable[FirstBaseline]

        // 받아온 패딩 dp 값에서 측정된 컴포저블 값을 빼면 할당시켜줄 노말한 패딩 값이 나옴
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        // 컴포저블 높이 값 + 노말한 패딩 값을 더하면 합쳐진 컴포저블의 높이 값이 나옴.
        val height = placeable.height + placeableY

        // 배치시키기
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)
