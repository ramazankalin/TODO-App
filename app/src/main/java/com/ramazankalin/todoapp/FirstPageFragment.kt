package com.ramazankalin.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.ramazankalin.todoapp.databinding.FragmentFirstPageBinding

class FirstPageFragment : Fragment() {

    private lateinit var binding: FragmentFirstPageBinding
    private lateinit var myAdapter: ToDoAdapter
    private val items = ArrayList<ItemDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItem(item: ItemDataModel) {
        items.add(item)
        myAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            val title = binding.editText.text.toString()
            val description = binding.editText2.text.toString()

            if (title.isNotBlank() && description.isNotBlank()) {
                val newItem = ItemDataModel(title, description)
                addItem(newItem)

                binding.editText.text.clear()
                binding.editText2.text.clear()

                binding.editText.requestFocus()
            }
            else {
                Snackbar.make(it, "Fill all the blanks", Snackbar.LENGTH_SHORT).show()
            }
        }
        myAdapter = ToDoAdapter(items)
        binding.recyclerView.adapter = myAdapter
    }
}