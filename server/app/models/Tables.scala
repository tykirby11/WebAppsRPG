package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Bosses.schema, Enemies.schema, Items.schema, Playerclass.schema, User.schema, Weapons.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Bosses
   *  @param bossid Database column bossid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hp Database column hp SqlType(INT)
   *  @param atk Database column atk SqlType(INT)
   *  @param spd Database column spd SqlType(INT) */
  case class BossesRow(bossid: Int, name: String, hp: Int, atk: Int, spd: Int)
  /** GetResult implicit for fetching BossesRow objects using plain SQL queries */
  implicit def GetResultBossesRow(implicit e0: GR[Int], e1: GR[String]): GR[BossesRow] = GR{
    prs => import prs._
    BossesRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table Bosses. Objects of this class serve as prototypes for rows in queries. */
  class Bosses(_tableTag: Tag) extends profile.api.Table[BossesRow](_tableTag, Some("rpg"), "Bosses") {
    def * = (bossid, name, hp, atk, spd) <> (BossesRow.tupled, BossesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(bossid), Rep.Some(name), Rep.Some(hp), Rep.Some(atk), Rep.Some(spd))).shaped.<>({r=>import r._; _1.map(_=> BossesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column bossid SqlType(INT), AutoInc, PrimaryKey */
    val bossid: Rep[Int] = column[Int]("bossid", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(30,true) */
    val name: Rep[String] = column[String]("name", O.Length(30,varying=true))
    /** Database column hp SqlType(INT) */
    val hp: Rep[Int] = column[Int]("hp")
    /** Database column atk SqlType(INT) */
    val atk: Rep[Int] = column[Int]("atk")
    /** Database column spd SqlType(INT) */
    val spd: Rep[Int] = column[Int]("spd")
  }
  /** Collection-like TableQuery object for table Bosses */
  lazy val Bosses = new TableQuery(tag => new Bosses(tag))

  /** Entity class storing rows of table Enemies
   *  @param enemyid Database column enemyid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hp Database column hp SqlType(INT)
   *  @param atk Database column atk SqlType(INT)
   *  @param spd Database column spd SqlType(INT) */
  case class EnemiesRow(enemyid: Int, name: String, hp: Int, atk: Int, spd: Int)
  /** GetResult implicit for fetching EnemiesRow objects using plain SQL queries */
  implicit def GetResultEnemiesRow(implicit e0: GR[Int], e1: GR[String]): GR[EnemiesRow] = GR{
    prs => import prs._
    EnemiesRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table Enemies. Objects of this class serve as prototypes for rows in queries. */
  class Enemies(_tableTag: Tag) extends profile.api.Table[EnemiesRow](_tableTag, Some("rpg"), "Enemies") {
    def * = (enemyid, name, hp, atk, spd) <> (EnemiesRow.tupled, EnemiesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(enemyid), Rep.Some(name), Rep.Some(hp), Rep.Some(atk), Rep.Some(spd))).shaped.<>({r=>import r._; _1.map(_=> EnemiesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column enemyid SqlType(INT), AutoInc, PrimaryKey */
    val enemyid: Rep[Int] = column[Int]("enemyid", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(30,true) */
    val name: Rep[String] = column[String]("name", O.Length(30,varying=true))
    /** Database column hp SqlType(INT) */
    val hp: Rep[Int] = column[Int]("hp")
    /** Database column atk SqlType(INT) */
    val atk: Rep[Int] = column[Int]("atk")
    /** Database column spd SqlType(INT) */
    val spd: Rep[Int] = column[Int]("spd")
  }
  /** Collection-like TableQuery object for table Enemies */
  lazy val Enemies = new TableQuery(tag => new Enemies(tag))

  /** Entity class storing rows of table Items
   *  @param itemid Database column itemid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hpmod Database column hpmod SqlType(INT)
   *  @param atkmod Database column atkmod SqlType(INT)
   *  @param spdmod Database column spdmod SqlType(INT)
   *  @param uses Database column uses SqlType(INT) */
  case class ItemsRow(itemid: Int, name: String, hpmod: Int, atkmod: Int, spdmod: Int, uses: Int)
  /** GetResult implicit for fetching ItemsRow objects using plain SQL queries */
  implicit def GetResultItemsRow(implicit e0: GR[Int], e1: GR[String]): GR[ItemsRow] = GR{
    prs => import prs._
    ItemsRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table Items. Objects of this class serve as prototypes for rows in queries. */
  class Items(_tableTag: Tag) extends profile.api.Table[ItemsRow](_tableTag, Some("rpg"), "Items") {
    def * = (itemid, name, hpmod, atkmod, spdmod, uses) <> (ItemsRow.tupled, ItemsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemid), Rep.Some(name), Rep.Some(hpmod), Rep.Some(atkmod), Rep.Some(spdmod), Rep.Some(uses))).shaped.<>({r=>import r._; _1.map(_=> ItemsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column itemid SqlType(INT), AutoInc, PrimaryKey */
    val itemid: Rep[Int] = column[Int]("itemid", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(30,true) */
    val name: Rep[String] = column[String]("name", O.Length(30,varying=true))
    /** Database column hpmod SqlType(INT) */
    val hpmod: Rep[Int] = column[Int]("hpmod")
    /** Database column atkmod SqlType(INT) */
    val atkmod: Rep[Int] = column[Int]("atkmod")
    /** Database column spdmod SqlType(INT) */
    val spdmod: Rep[Int] = column[Int]("spdmod")
    /** Database column uses SqlType(INT) */
    val uses: Rep[Int] = column[Int]("uses")
  }
  /** Collection-like TableQuery object for table Items */
  lazy val Items = new TableQuery(tag => new Items(tag))

  /** Entity class storing rows of table Playerclass
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hp Database column hp SqlType(INT)
   *  @param atk Database column atk SqlType(INT)
   *  @param spd Database column spd SqlType(INT) */
  case class PlayerclassRow(id: Int, name: String, hp: Int, atk: Int, spd: Int)
  /** GetResult implicit for fetching PlayerclassRow objects using plain SQL queries */
  implicit def GetResultPlayerclassRow(implicit e0: GR[Int], e1: GR[String]): GR[PlayerclassRow] = GR{
    prs => import prs._
    PlayerclassRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table PlayerClass. Objects of this class serve as prototypes for rows in queries. */
  class Playerclass(_tableTag: Tag) extends profile.api.Table[PlayerclassRow](_tableTag, Some("rpg"), "PlayerClass") {
    def * = (id, name, hp, atk, spd) <> (PlayerclassRow.tupled, PlayerclassRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(hp), Rep.Some(atk), Rep.Some(spd))).shaped.<>({r=>import r._; _1.map(_=> PlayerclassRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(30,true) */
    val name: Rep[String] = column[String]("name", O.Length(30,varying=true))
    /** Database column hp SqlType(INT) */
    val hp: Rep[Int] = column[Int]("hp")
    /** Database column atk SqlType(INT) */
    val atk: Rep[Int] = column[Int]("atk")
    /** Database column spd SqlType(INT) */
    val spd: Rep[Int] = column[Int]("spd")
  }
  /** Collection-like TableQuery object for table Playerclass */
  lazy val Playerclass = new TableQuery(tag => new Playerclass(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(VARCHAR), Length(20,true)
   *  @param password Database column password SqlType(VARCHAR), Length(20,true) */
  case class UserRow(id: Int, username: String, password: String)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table User. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("rpg"), "User") {
    def * = (id, username, password) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password))).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(VARCHAR), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(VARCHAR), Length(20,true) */
    val password: Rep[String] = column[String]("password", O.Length(20,varying=true))
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table Weapons
   *  @param weaponid Database column weaponid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hpmod Database column hpmod SqlType(INT)
   *  @param atkmod Database column atkmod SqlType(INT)
   *  @param spdmod Database column spdmod SqlType(INT) */
  case class WeaponsRow(weaponid: Int, name: String, hpmod: Int, atkmod: Int, spdmod: Int)
  /** GetResult implicit for fetching WeaponsRow objects using plain SQL queries */
  implicit def GetResultWeaponsRow(implicit e0: GR[Int], e1: GR[String]): GR[WeaponsRow] = GR{
    prs => import prs._
    WeaponsRow.tupled((<<[Int], <<[String], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table Weapons. Objects of this class serve as prototypes for rows in queries. */
  class Weapons(_tableTag: Tag) extends profile.api.Table[WeaponsRow](_tableTag, Some("rpg"), "Weapons") {
    def * = (weaponid, name, hpmod, atkmod, spdmod) <> (WeaponsRow.tupled, WeaponsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(weaponid), Rep.Some(name), Rep.Some(hpmod), Rep.Some(atkmod), Rep.Some(spdmod))).shaped.<>({r=>import r._; _1.map(_=> WeaponsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column weaponid SqlType(INT), AutoInc, PrimaryKey */
    val weaponid: Rep[Int] = column[Int]("weaponid", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(30,true) */
    val name: Rep[String] = column[String]("name", O.Length(30,varying=true))
    /** Database column hpmod SqlType(INT) */
    val hpmod: Rep[Int] = column[Int]("hpmod")
    /** Database column atkmod SqlType(INT) */
    val atkmod: Rep[Int] = column[Int]("atkmod")
    /** Database column spdmod SqlType(INT) */
    val spdmod: Rep[Int] = column[Int]("spdmod")
  }
  /** Collection-like TableQuery object for table Weapons */
  lazy val Weapons = new TableQuery(tag => new Weapons(tag))
}
