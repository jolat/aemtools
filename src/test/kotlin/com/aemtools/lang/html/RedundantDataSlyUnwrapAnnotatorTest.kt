package com.aemtools.lang.html

import com.aemtools.lang.html.annotation.RedundantDataSlyUnwrapAnnotator.Companion.REDUNDANT_DATA_SLY_UNWRAP_MESSAGE
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

/**
 * @author Dmytro Troynikov
 */
class RedundantDataSlyUnwrapAnnotatorTest : LightPlatformCodeInsightFixtureTestCase() {

    fun testRedundantDataSlyUnwrap() {
        myFixture.configureByText("test.html", """
            <sly <warning descr="$REDUNDANT_DATA_SLY_UNWRAP_MESSAGE">data-sly-unwrap</warning>> </sly>
        """)
        myFixture.checkHighlighting()
    }

    fun testRedundantDataSlyUnwrap2() {
        myFixture.configureByText("test.html", """
            <sly class="test"
                 data-sly-use.bean="com.test.Bean"
                 <warning descr="$REDUNDANT_DATA_SLY_UNWRAP_MESSAGE">data-sly-unwrap</warning>>
            </sly>
        """)
        myFixture.checkHighlighting()
    }

    fun testNestedUnwrap() {
        myFixture.configureByText("test.html", """
            <sly>
                <div data-sly-unwrap></div>
            </sly>
        """)
        myFixture.checkHighlighting()
    }

    fun testDataSlyUseVariable() {
        myFixture.configureByText("test.html", """
            <div data-sly-use.bean="com.test.Bean">
            </div>
        """)
        myFixture.checkHighlighting()
    }
}