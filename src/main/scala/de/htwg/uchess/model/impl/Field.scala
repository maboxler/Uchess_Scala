package de.htwg.uchess.model.impl

import de.htwg.uchess.model.Piece
import de.htwg.uchess.util.Point


case class Field(val point: Point, var optionChessPiece: Option[Piece] = None) {
}