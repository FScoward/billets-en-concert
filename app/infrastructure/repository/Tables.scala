package infrastructure.repository

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{ GetResult => GR }

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Artists.schema ++ Lives.schema ++ LivesHasArtists.schema ++ Places.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /**
   * Entity class storing rows of table Artists
   *  @param artistId Database column ARTIST_ID SqlType(BIGINT), PrimaryKey
   *  @param artistName Database column ARTIST_NAME SqlType(VARCHAR), Length(45,true)
   */
  case class ArtistsRow(artistId: Long, artistName: String)
  /** GetResult implicit for fetching ArtistsRow objects using plain SQL queries */
  implicit def GetResultArtistsRow(implicit e0: GR[Long], e1: GR[String]): GR[ArtistsRow] = GR {
    prs =>
      import prs._
      ArtistsRow.tupled((<<[Long], <<[String]))
  }
  /** Table description of table artists. Objects of this class serve as prototypes for rows in queries. */
  class Artists(_tableTag: Tag) extends Table[ArtistsRow](_tableTag, "artists") {
    def * = (artistId, artistName) <> (ArtistsRow.tupled, ArtistsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(artistId), Rep.Some(artistName)).shaped.<>({ r => import r._; _1.map(_ => ArtistsRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column ARTIST_ID SqlType(BIGINT), PrimaryKey */
    val artistId: Rep[Long] = column[Long]("ARTIST_ID", O.PrimaryKey)
    /** Database column ARTIST_NAME SqlType(VARCHAR), Length(45,true) */
    val artistName: Rep[String] = column[String]("ARTIST_NAME", O.Length(45, varying = true))

    /** Uniqueness Index over (artistName) (database name ARTIST_NAME_UNIQUE) */
    val index1 = index("ARTIST_NAME_UNIQUE", artistName, unique = true)
  }
  /** Collection-like TableQuery object for table Artists */
  lazy val Artists = new TableQuery(tag => new Artists(tag))

  /**
   * Entity class storing rows of table Lives
   *  @param liveId Database column LIVE_ID SqlType(BIGINT), PrimaryKey
   *  @param liveTitle Database column LIVE_TITLE SqlType(VARCHAR), Length(45,true)
   *  @param startTime Database column START_TIME SqlType(DATETIME), Default(None)
   *  @param endTime Database column END_TIME SqlType(DATETIME), Default(None)
   *  @param placeId Database column PLACE_ID SqlType(BIGINT)
   */
  case class LivesRow(liveId: Long, liveTitle: String, startTime: Option[java.sql.Timestamp] = None, endTime: Option[java.sql.Timestamp] = None, placeId: Long)
  /** GetResult implicit for fetching LivesRow objects using plain SQL queries */
  implicit def GetResultLivesRow(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Timestamp]]): GR[LivesRow] = GR {
    prs =>
      import prs._
      LivesRow.tupled((<<[Long], <<[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp], <<[Long]))
  }
  /** Table description of table lives. Objects of this class serve as prototypes for rows in queries. */
  class Lives(_tableTag: Tag) extends Table[LivesRow](_tableTag, "lives") {
    def * = (liveId, liveTitle, startTime, endTime, placeId) <> (LivesRow.tupled, LivesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(liveId), Rep.Some(liveTitle), startTime, endTime, Rep.Some(placeId)).shaped.<>({ r => import r._; _1.map(_ => LivesRow.tupled((_1.get, _2.get, _3, _4, _5.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column LIVE_ID SqlType(BIGINT), PrimaryKey */
    val liveId: Rep[Long] = column[Long]("LIVE_ID", O.PrimaryKey)
    /** Database column LIVE_TITLE SqlType(VARCHAR), Length(45,true) */
    val liveTitle: Rep[String] = column[String]("LIVE_TITLE", O.Length(45, varying = true))
    /** Database column START_TIME SqlType(DATETIME), Default(None) */
    val startTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("START_TIME", O.Default(None))
    /** Database column END_TIME SqlType(DATETIME), Default(None) */
    val endTime: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("END_TIME", O.Default(None))
    /** Database column PLACE_ID SqlType(BIGINT) */
    val placeId: Rep[Long] = column[Long]("PLACE_ID")

    /** Foreign key referencing Places (database name fk_LIVE_PLACE) */
    lazy val placesFk = foreignKey("fk_LIVE_PLACE", placeId, Places)(r => r.placeId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)

    /** Uniqueness Index over (liveTitle) (database name LIVE_TITLE_UNIQUE) */
    val index1 = index("LIVE_TITLE_UNIQUE", liveTitle, unique = true)
  }
  /** Collection-like TableQuery object for table Lives */
  lazy val Lives = new TableQuery(tag => new Lives(tag))

  /**
   * Entity class storing rows of table LivesHasArtists
   *  @param livesLiveId Database column LIVES_LIVE_ID SqlType(BIGINT)
   *  @param artistsArtistId Database column ARTISTS_ARTIST_ID SqlType(BIGINT)
   */
  case class LivesHasArtistsRow(livesLiveId: Long, artistsArtistId: Long)
  /** GetResult implicit for fetching LivesHasArtistsRow objects using plain SQL queries */
  implicit def GetResultLivesHasArtistsRow(implicit e0: GR[Long]): GR[LivesHasArtistsRow] = GR {
    prs =>
      import prs._
      LivesHasArtistsRow.tupled((<<[Long], <<[Long]))
  }
  /** Table description of table lives_has_artists. Objects of this class serve as prototypes for rows in queries. */
  class LivesHasArtists(_tableTag: Tag) extends Table[LivesHasArtistsRow](_tableTag, "lives_has_artists") {
    def * = (livesLiveId, artistsArtistId) <> (LivesHasArtistsRow.tupled, LivesHasArtistsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(livesLiveId), Rep.Some(artistsArtistId)).shaped.<>({ r => import r._; _1.map(_ => LivesHasArtistsRow.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column LIVES_LIVE_ID SqlType(BIGINT) */
    val livesLiveId: Rep[Long] = column[Long]("LIVES_LIVE_ID")
    /** Database column ARTISTS_ARTIST_ID SqlType(BIGINT) */
    val artistsArtistId: Rep[Long] = column[Long]("ARTISTS_ARTIST_ID")

    /** Primary key of LivesHasArtists (database name lives_has_artists_PK) */
    val pk = primaryKey("lives_has_artists_PK", (livesLiveId, artistsArtistId))

    /** Foreign key referencing Artists (database name fk_LIVES_has_ARTISTS_ARTISTS1) */
    lazy val artistsFk = foreignKey("fk_LIVES_has_ARTISTS_ARTISTS1", artistsArtistId, Artists)(r => r.artistId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
    /** Foreign key referencing Lives (database name fk_LIVES_has_ARTISTS_LIVES1) */
    lazy val livesFk = foreignKey("fk_LIVES_has_ARTISTS_LIVES1", livesLiveId, Lives)(r => r.liveId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table LivesHasArtists */
  lazy val LivesHasArtists = new TableQuery(tag => new LivesHasArtists(tag))

  /**
   * Entity class storing rows of table Places
   *  @param placeId Database column PLACE_ID SqlType(BIGINT), PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(100,true)
   *  @param address Database column ADDRESS SqlType(VARCHAR), Length(100,true)
   */
  case class PlacesRow(placeId: Long, name: String, address: String)
  /** GetResult implicit for fetching PlacesRow objects using plain SQL queries */
  implicit def GetResultPlacesRow(implicit e0: GR[Long], e1: GR[String]): GR[PlacesRow] = GR {
    prs =>
      import prs._
      PlacesRow.tupled((<<[Long], <<[String], <<[String]))
  }
  /** Table description of table places. Objects of this class serve as prototypes for rows in queries. */
  class Places(_tableTag: Tag) extends Table[PlacesRow](_tableTag, "places") {
    def * = (placeId, name, address) <> (PlacesRow.tupled, PlacesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(placeId), Rep.Some(name), Rep.Some(address)).shaped.<>({ r => import r._; _1.map(_ => PlacesRow.tupled((_1.get, _2.get, _3.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column PLACE_ID SqlType(BIGINT), PrimaryKey */
    val placeId: Rep[Long] = column[Long]("PLACE_ID", O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(100,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(100, varying = true))
    /** Database column ADDRESS SqlType(VARCHAR), Length(100,true) */
    val address: Rep[String] = column[String]("ADDRESS", O.Length(100, varying = true))

    /** Uniqueness Index over (address) (database name ADDRESS_UNIQUE) */
    val index1 = index("ADDRESS_UNIQUE", address, unique = true)
  }
  /** Collection-like TableQuery object for table Places */
  lazy val Places = new TableQuery(tag => new Places(tag))
}
