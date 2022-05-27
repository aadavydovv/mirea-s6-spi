package ru.mirea.kanban.fragments.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.mirea.kanban.MainActivity
import ru.mirea.kanban.R
import ru.mirea.kanban.room.comment.EntityComment
import ru.mirea.kanban.room.kanbanTask.DBClientKanbanTask
import ru.mirea.kanban.room.kanbanTask.EntityKanbanTask


class CommentListAdapter(
    private val userEntries: List<EntityComment>
) :
    RecyclerView.Adapter<CommentListAdapter.CommentEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_comment_entry, parent, false)

        return CommentEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentEntryViewHolder, position: Int) {
        holder.bind(userEntries[position])
    }

    override fun getItemCount() = userEntries.size

    class CommentEntryViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val textOfComment: TextView = itemView.findViewById(R.id.textViewCommentText)

        fun bind(commentEntry: EntityComment) {
            textOfComment.text = commentEntry.text
        }
    }
}