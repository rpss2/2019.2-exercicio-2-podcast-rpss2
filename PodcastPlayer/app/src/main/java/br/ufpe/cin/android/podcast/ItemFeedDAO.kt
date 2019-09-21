package br.ufpe.cin.android.podcast

import androidx.room.*

@Dao
interface ItemFeedDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun pushItem(vararg p: ItemFeed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun pushList(list: List<ItemFeed>)

    @Query("SELECT * FROM podcasts")
    fun getAll() : List<ItemFeed>

    @Query("SELECT * FROM podcasts WHERE title LIKE :q")
    fun getByTitle(q: String) : List<ItemFeed>
}