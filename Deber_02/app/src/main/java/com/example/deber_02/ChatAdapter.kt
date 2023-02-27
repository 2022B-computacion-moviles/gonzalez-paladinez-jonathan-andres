package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private val chats: List<ChatMessage>,
    private val contexto: StartUpActivity,
    private val recycler: RecyclerView
    ) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    var onItemClick: ((ChatMessage) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sender = chats[position].sender
        val message = chats[position].message
        val time = chats[position].time
        val count = chats[position].count
        holder.putData(sender, message, time, count)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(chats[position])
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView
        val messageTextView: TextView
        val timeMessageTextView: TextView
        val countMessageTextView: TextView

        init {
            senderTextView = itemView.findViewById(R.id.senderTextView)
            messageTextView = itemView.findViewById(R.id.messageTextView)
            timeMessageTextView = itemView.findViewById(R.id.timeMessageTextView)
            countMessageTextView = itemView.findViewById(R.id.countMeesagesTextView)
        }

        fun putData(sender: String?, message: String?, time: String?, count: String?){
            senderTextView.text = sender
            messageTextView.text = message
            timeMessageTextView.text = time
            countMessageTextView.text = count
        }

    }
}
