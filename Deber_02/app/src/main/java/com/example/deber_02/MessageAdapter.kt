package com.example.deber_02


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(
    private val messages: List<Message>,
    private val contexto: ChatActivity,
    private val recycler: RecyclerView
    ) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position].message
        val time = messages[position].time

        holder.putData(message, time)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val messageTextView: TextView
        private val timeMessageTextView: TextView

        init {
            messageTextView =  itemView.findViewById(R.id.messageChatTextView)
            timeMessageTextView = itemView.findViewById(R.id.timeMessageChatTextView)
        }

        fun putData(message: String?, time: String?){
            messageTextView.text = message
            timeMessageTextView.text = time
        }

    }
}
