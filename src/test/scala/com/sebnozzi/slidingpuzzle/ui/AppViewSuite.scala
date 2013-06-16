package com.sebnozzi.slidingpuzzle.ui

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.sebnozzi.slidingpuzzle.model.GridSize
class AppViewSuite extends FunSuite with BeforeAndAfter {

  class TestAppView extends AppView {
    var hasPuzzleView = false
    def setPuzzleView(puzzleView: PuzzleView) {
      hasPuzzleView = true
    }
  }

  var appView: TestAppView = _

  before {
    appView = new TestAppView()
  }

  test("handles shuffle click") {
    var called = false
    appView.onShuffleClicked {
      called = true
    }
    appView.shuffleClicked()
    assert(called, "was not called")
  }

  test("handles shuffle click when no callback") {
    appView.shuffleClicked()
  }

  test("handles reset click") {
    var called = false
    appView.onResetClicked {
      called = true
    }
    appView.resetClicked()
    assert(called, "was not called")
  }

  test("handles reset click when no callback") {
    appView.resetClicked()
  }

  test("handles size change") {
    var called = false
    val newSize = GridSize(columns = 4, rows = 3)
    appView.onNewSizeSelected { newSizeSelected =>
      assert(newSizeSelected === newSize)
      called = true
    }
    appView.newSizeSelected(newSize)
    assert(called, "was not called")
  }

  test("handles size change when no callback") {
    val newSize = GridSize(columns = 4, rows = 3)
    appView.newSizeSelected(newSize)
  }

  def assertKeyPressed(key: ArrowKey) {
    var pressed = false
    appView.onArrowKeyPressed { arrowKey =>
      if (arrowKey == key)
        pressed = true
    }
    appView.arrowKeyPressed(key)
    assert(pressed, "key was not handled: " + key)
  }

  test("handles arrow keys, with no callback") {
    appView.arrowKeyPressed(Up)
  }

  test("handles arrow keys") {
    assertKeyPressed(Up)
    assertKeyPressed(Down)
    assertKeyPressed(Left)
    assertKeyPressed(Right)
  }

  test("can be set a puzzle-view") {
    val puzzleView = new PuzzleView(GridSize(3,3)) {}
    appView.setPuzzleView(puzzleView)
    assert(appView.hasPuzzleView)
  }

}