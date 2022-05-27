package ru.mirea.kanban.fragments.files

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.kanban.R
import ru.mirea.kanban.room.file.EntityFile

class FilesAdapter(
    private val userEntries: List<EntityFile>
) :
    RecyclerView.Adapter<FilesAdapter.FileEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_file_entry, parent, false)

        return FileEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileEntryViewHolder, position: Int) {
        holder.bind(userEntries[position])
    }

    override fun getItemCount() = userEntries.size

    class FileEntryViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameOfFile: TextView = itemView.findViewById(R.id.textViewFileName)

        fun bind(fileEntry: EntityFile) {
            nameOfFile.text = fileEntry.name
        }
    }
}