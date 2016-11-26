package domain.model

/**
 * Created by Fumiyasu on 2016/11/21.
 */
trait Ticket {
  val ticketId: Long
  val liveId: Long
  val seatNo: Option[String]
}

case class AssignedTicket(ticketId: Long, liveId: Long, seatNo: Option[String], userId: Long) extends Ticket
case class UnAssignedTicket(ticketId: Long, liveId: Long, seatNo: Option[String])
