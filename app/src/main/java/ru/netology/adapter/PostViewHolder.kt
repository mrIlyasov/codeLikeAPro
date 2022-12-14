package ru.netology.adapter

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.dataClasses.Post
import ru.netology.R
import ru.netology.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onInteractionListener: OnInteractionListener

    ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.authorName
            date.text = post.date
            content.text = post.content
            likeButton.text=rounding(post.likes)
            viewsCountTextView.text = rounding(post.views)
            shareButton.text = rounding(post.repostsCount)
            likeButton.isChecked=post.likedByMe
            if (post.youtubeVideo!=null){
                buttonImageGroup.visibility= View.VISIBLE
            }
            else buttonImageGroup.visibility = View.GONE

            likeButton.setOnClickListener{
                onInteractionListener.onLike(post)
            }
            likeButton.setOnClickListener {
               onInteractionListener.onLike(post)
            }
            shareButton.setOnClickListener {
                onInteractionListener.onRepost(post)
            }
            viewsButton.setOnClickListener {
                onInteractionListener.onView(post)
            }
            playButton.setOnClickListener{
                onInteractionListener.onYoutubeClick(post)
            }
            imageView.setOnClickListener{
                onInteractionListener.onYoutubeClick(post)
            }

            menuButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {

                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }


    fun rounding(value: Int): String {
        if (value > 999 && value < 10000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "K"
            return (stringToReturn)
        } else if (value >= 10000 && value < 100_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]

            var stringToReturn = "$symbol1" + "$symbol2" + "K"
            return (stringToReturn)
        } else if (value >= 100000 && value < 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var symbol3 = value.toString()[2]
            var stringToReturn = "$symbol1" + "$symbol2" + "$symbol3" + "K"
            return (stringToReturn)
        } else if (value > 1_000_000) {
            var symbol1 = value.toString()[0]
            var symbol2 = value.toString()[1]
            var stringToReturn = "$symbol1" + "," + "$symbol2" + "M"
            return (stringToReturn)
        } else return value.toString()
    }
}
