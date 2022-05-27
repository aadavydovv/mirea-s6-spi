package ru.mirea.kanban.fragments.files

import android.R.attr
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.mirea.kanban.R
import ru.mirea.kanban.room.file.DBClientFile
import ru.mirea.kanban.room.file.EntityFile


fun Uri.getName(context: Context): String {
    val returnCursor = context.contentResolver.query(this, null, null, null, null)
    val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val fileName = returnCursor.getString(nameIndex)
    returnCursor.close()
    return fileName
}


class FragmentFiles : Fragment() {

    private val args: FragmentFilesArgs by navArgs()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uri: Uri = data?.data!!
        val src = uri.path
        val fileName = uri.getName(requireContext())

        val dbClient = DBClientFile(requireActivity())
        val newFile = EntityFile(fileName, requestCode, src!!)
        lifecycleScope.launch {
            dbClient.insertAll(newFile)
        }
        dbClient.awaitResult()

        findNavController().popBackStack()

//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()))
//        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_files, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbClient = DBClientFile(requireActivity())
        lifecycleScope.launch {
            dbClient.getByTaskID(args.taskID)
        }
        val files = dbClient.awaitResult() as MutableList<EntityFile>

        val recycler: RecyclerView = view.findViewById(R.id.recycler_files)
        val adapter = FilesAdapter(files)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        view.findViewById<Button>(R.id.buttonFilesAdd).setOnClickListener {
            var chooseFile = Intent(Intent.ACTION_GET_CONTENT)
            chooseFile.type = "*/*"
            chooseFile = Intent.createChooser(chooseFile, "Choose a file")
            startActivityForResult(chooseFile, args.taskID)

//            // Request code for selecting a PDF document.
//            val PICK_PDF_FILE = 2
//
//            fun openFile(pickerInitialUri: Uri) {
//                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//                    addCategory(Intent.CATEGORY_OPENABLE)
//                    type = "application/pdf"
//                    putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
//                }
//
//                startActivityForResult(intent, PICK_PDF_FILE)
//            }
//
//            openFile(requireContext().filesDir.toUri())



//            val fileName = view.findViewById<TextInputLayout>(R.id.textInputLayoutFileInput).editText?.text.toString()

//            lifecycleScope.launch {
//                dbClient.insertAll(newComment)
//            }
//            dbClient.awaitResult()
//
//            comments.add(newComment)
//            adapter.notifyItemInserted(comments.size - 1)
        }
    }
}