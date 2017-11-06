package de.htwg.uchess.model.impl

import de.htwg.uchess.model.Piece
import de.htwg.uchess.util.Point

case class Bishop( color: Char) extends Piece {
  override def possibleMove(gameField: GameField) = ???
  override def toString: String = {
    color match {
      case 'w' => "♗"
      case 'b' => "♝"
      case _   => "B" + color
    }
  }
}