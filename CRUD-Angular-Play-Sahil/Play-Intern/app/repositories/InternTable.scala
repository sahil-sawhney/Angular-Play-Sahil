package repositories

/**
  * Created by sahil on 3/14/16.
  */

import com.google.inject.Inject
import models.Intern
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait InternTable {self: HasDatabaseConfigProvider[JdbcProfile] =>

  import driver.api._

  class InternTable (tag:Tag) extends Table[Intern](tag,"intern_details"){

    val name=column[String]("name", O.SqlType("VARCHAR(50)"))
    val email=column[String]("email", O.SqlType("VARCHAR(50)"))
    val mobile=column[String]("mobile", O.SqlType("VARCHAR(50)"))
    val address=column[String]("address", O.SqlType("VARCHAR(50)"))
    val alternateContact=column[String]("alternate_contact", O.SqlType("VARCHAR(50)"))
    val id=column[Int]("id", O.PrimaryKey, O.AutoInc)

    def * = (name,email,mobile,address,alternateContact,id) <> (Intern.tupled, Intern.unapply)
  }

  val internTable= TableQuery[InternTable]

}


class InternRepo @Inject()(protected val dbConfigProvider:DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] with InternTable {

  import driver.api._

  def getAll: Future[List[Intern]] = db.run {
    internTable.to[List].result
  }

  def insert(intern: Intern): Future[Int] = db.run {
    internTable += Intern(intern.name, intern.email, intern.mobile, intern.address, intern.alternateContact)
  }

  def update(intern: Intern): Future[Int] = db.run {
    internTable.filter(_.id === intern.id).update(intern)
  }

  def delete(id: Int): Future[Int] = db.run {
    internTable.filter(_.id === id).delete
  }

  def internById(id: Int): Future[Intern] = db.run {
    internTable.filter(_.id === id).result.head
  }

}


