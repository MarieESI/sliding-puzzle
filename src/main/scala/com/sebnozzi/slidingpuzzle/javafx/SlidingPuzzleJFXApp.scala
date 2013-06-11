package com.sebnozzi.slidingpuzzle.javafx

import _root_.javafx.application.Application
import Implicits._
import javafx.scene.layout.HBox
import javafx.stage.Stage
import javafx.scene.image.Image

class SlidingPuzzleJFXApp extends Application {

  val columns = 4
  val rows = 3

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def start(mainWindow: Stage) {
    val mainGroup = new HBox()
    val buttonsPanel = new ButtonsPanel()
    val tilesBoard = new TilesBoard(img, columns, rows)

    buttonsPanel.onResetPressed(resetPressed)
    buttonsPanel.onShufflePressed(shufflePressed)

    mainGroup.getChildren().add(tilesBoard)
    mainGroup.getChildren().add(buttonsPanel)
    
    mainWindow.setupWithGroup(mainGroup)
    mainWindow.show()
  }


  private def shufflePressed() {
    println("Shuffle pressed")
  }

  private def resetPressed() {
    println("Reset pressed")
  }

}
