package ru.netology.repository

import androidx.lifecycle.LiveData
import ru.netology.dataClasses.Post

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun getSize(): Int
    fun like(id: Int)
    fun repost(id: Int)
    fun view(id: Int)
    fun removeById(id: Int)
    fun edit(id: Int, newContent: String)
    fun savePost(post: Post)

    fun findIndexOfPostById(id:Int): Int
    fun findPost(id: Int): Post?
    fun addNextId()

}