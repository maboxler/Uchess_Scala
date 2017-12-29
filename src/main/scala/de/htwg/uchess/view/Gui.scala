package de.htwg.uchess.view

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Color, Dimension, GridLayout}
import javax.swing.{ImageIcon, JFrame}

import de.htwg.uchess.controller.Controller
import de.htwg.uchess.util.{PieceButton, Point}

import scala.collection.mutable.ListBuffer

class Gui(c: Controller) {

  val blackFieldColor = new Color(185, 122, 87)
  val whiteFieldColor = new Color(239, 227, 175)
  var firstClick = false
  var startPosi  = new PieceButton(None,new Point(-1,-1))
  var TargetPosi = new PieceButton(None,new Point(-1,-1))

  JFrame.setDefaultLookAndFeelDecorated(true)
  val frame: JFrame = new JFrame()
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frame.setLayout(new GridLayout(8, 8))

  val buttons = new ListBuffer[PieceButton]()
  initPieceButtons()


  for (x <- 0 to 63) {
    buttons(x).setPreferredSize(new Dimension(60, 60))
    buttons(x).addActionListener(new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = {
        //if(c.checkWin()){


        if(!firstClick) {
          frame.setTitle("")
          firstClick = !firstClick
          startPosi = buttons(x)
        } else {
          firstClick = !firstClick
          TargetPosi = buttons(x)
          val move = c.move(startPosi.pos,TargetPosi.pos)
          if(move) {
            TargetPosi.figure = startPosi.figure
            startPosi.figure = None
            drawIconBoard
          } else {
            frame.setTitle(frame.getTitle + " - invalid Move")
          }
        }
    //    }
      }
    })
  }

  for (x <- 0 to 7) {
    if (x % 2 == 0) {
      buttons(x).setBackground(whiteFieldColor)
      buttons(x + 16).setBackground(whiteFieldColor)
      buttons(x + 32).setBackground(whiteFieldColor)
      buttons(x + 48).setBackground(whiteFieldColor)

      buttons(x + 9).setBackground(whiteFieldColor)
      buttons(x + 25).setBackground(whiteFieldColor)
      buttons(x + 41).setBackground(whiteFieldColor)
      buttons(x + 57).setBackground(whiteFieldColor)

      buttons(x + 8).setBackground(blackFieldColor)
      buttons(x + 24).setBackground(blackFieldColor)
      buttons(x + 40).setBackground(blackFieldColor)
      buttons(x + 56).setBackground(blackFieldColor)
    } else {
      buttons(x).setBackground(blackFieldColor)
      buttons(x + 16).setBackground(blackFieldColor)
      buttons(x + 32).setBackground(blackFieldColor)
      buttons(x + 48).setBackground(blackFieldColor)
    }
  }

  drawIconBoard
  frame.pack()
  frame.setVisible(true)

  private def initPieceButtons() = {
    buttons += new PieceButton(Some("bTower"), new Point(0, 0))
    buttons += new PieceButton(Some("bKnight"), new Point(1, 0))
    buttons += new PieceButton(Some("bBishop"), new Point(2, 0))
    buttons += new PieceButton(Some("bQueen"), new Point(3, 0))
    buttons += new PieceButton(Some("bKing"), new Point(4, 0))
    buttons += new PieceButton(Some("bBishop"), new Point(5, 0))
    buttons += new PieceButton(Some("bKnight"), new Point(6, 0))
    buttons += new PieceButton(Some("bTower"), new Point(7, 0))

    for (i <- 0 to 7)
      buttons += new PieceButton(Some("bPawn"), new Point(i, 1))
    for (i <- 0 to 7)
      buttons += new PieceButton(None, new Point(i, 2))
    for (i <- 0 to 7)
      buttons += new PieceButton(None, new Point(i, 3))
    for (i <- 0 to 7)
      buttons += new PieceButton(None, new Point(i, 4))
    for (i <- 0 to 7)
      buttons += new PieceButton(None, new Point(i, 5))

    for (i <- 0 to 7)
      buttons += new PieceButton(Some("wPawn"), new Point(i, 6))

    buttons += new PieceButton(Some("wTower"), new Point(0, 7))
    buttons += new PieceButton(Some("wKnight"), new Point(1, 7))
    buttons += new PieceButton(Some("wBishop"), new Point(2, 7))
    buttons += new PieceButton(Some("wQueen"), new Point(3, 7))
    buttons += new PieceButton(Some("wKing"), new Point(4, 7))
    buttons += new PieceButton(Some("wBishop"), new Point(5, 7))
    buttons += new PieceButton(Some("wKnight"), new Point(6, 7))
    buttons += new PieceButton(Some("wTower"), new Point(7, 7))
  }
  private def setBtnIcon(btn: PieceButton, icon: String): Unit = {
    try {
      val image = new ImageIcon(this.getClass.getResource(icon)).getImage
      btn.setIcon(new ImageIcon(image))
    } catch {
      case ex: Exception =>
        System.out.println(ex)
    }
  }
  private def drawIconBoard = {
    for (n <- 0 to 63) {
      frame.add(buttons(n))
      buttons(n).figure match {
        case Some(f) => setBtnIcon(buttons(n), "resources/" + f + ".png")
        case None => buttons(n).setIcon(null)
      }
    }
  }

}