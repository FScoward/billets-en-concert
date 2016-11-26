package domain.model

/**
 * Created by Fumiyasu on 2016/11/21.
 */
case class Group(groupId: Long, liveId: Long, name: String, isPrivate: Boolean)
case class Owner(userId: Long, groupId: Long)
case class Member(userId: Long, groupId: Long)
