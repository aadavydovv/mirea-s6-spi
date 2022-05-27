package ru.mirea.kanban.fragments.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.fragments.users.UserListAdapter
import ru.mirea.kanban.room.comment.DBClientComment
import ru.mirea.kanban.room.comment.EntityComment

class FragmentCommentList : Fragment() {

    private val args: FragmentCommentListArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbClient = DBClientComment(requireActivity())
        lifecycleScope.launch {
            dbClient.getByTaskID(args.taskID)
        }
        val comments = dbClient.awaitResult() as MutableList<EntityComment>

        val recycler: RecyclerView = view.findViewById(R.id.recycler_comments)
        val adapter = CommentListAdapter(comments)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.buttonCommentsAdd).setOnClickListener {
            val commentText = view.findViewById<TextInputLayout>(R.id.textInputLayoutCommentsInput).editText?.text.toString()
            val newComment = EntityComment(commentText, args.taskID)

            lifecycleScope.launch {
                dbClient.insertAll(newComment)
            }
            dbClient.awaitResult()

            comments.add(newComment)
            adapter.notifyItemInserted(comments.size - 1)
        }
    }
}