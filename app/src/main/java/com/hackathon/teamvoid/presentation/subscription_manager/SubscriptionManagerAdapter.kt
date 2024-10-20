import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

class SubscriptionManagerAdapter(private var list: List<Transaction>,private val onItemClick: (Transaction) -> Unit) : RecyclerView.Adapter<SubscriptionManagerAdapter.ViewHolder>() {

    private var totalSum: Double = 0.0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val upcomingDate: TextView = itemView.findViewById(R.id.tv_subscription_date2)
        val img: ImageView = itemView.findViewById(R.id.img_subscription2)
        val name: TextView = itemView.findViewById(R.id.tv_subscription_name2)
        val price: TextView = itemView.findViewById(R.id.tv_subscription_price2)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(list[position])
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_subscription_manager, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        // Update total sum for the current item's amount
        updateTotalSum(currentItem.amount)

        holder.upcomingDate.text = addOneMonthAndFormat(currentItem.date)
        holder.price.text = currentItem.amount.replace("-", "").trim()
        holder.name.text = currentItem.company

        when(currentItem.company){
                "Spotify" -> {
                    Glide.with(holder.img.context)
                    .load(R.drawable.spotify).into(holder.img)}
                "Google" ->{
                    Glide.with(holder.img.context)
                        .load(R.drawable.google_logo).into(holder.img)}
                "Cavea Plus" ->{
                    Glide.with(holder.img.context).load(R.drawable.cavea_logo).into(holder.img)}
                "Glovo Prime" ->{
                    Glide.with(holder.img.context
                    ).load(R.drawable.glovo).into(holder.img)}
                "Netflix"->{
                    Glide.with(holder.img.context
                    ).load(R.drawable.netflix_logo).into(holder.img)}

        }

    }

    fun updateList(newList: List<Transaction>) {
        list = newList
        totalSum = 0.0 // Reset total sum
        notifyDataSetChanged()
    }

    private fun addOneMonthAndFormat(timeInMillis: Long): String {
        val calendar = Calendar.getInstance().apply {
            setTimeInMillis(timeInMillis)
            add(Calendar.MONTH, 1)
        }
        val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    private fun updateTotalSum(amount: String) {
        // Remove negative symbol if present
        val cleanedAmount = amount.replace("-", "").trim()

        // Check if amount contains dollar sign or GEL sign
        when {
            cleanedAmount.contains("$") -> {
                // Remove $ symbol and multiply by 2.72
                val value = cleanedAmount.replace("$", "").toDoubleOrNull()
                if (value != null) {
                    totalSum += value * 2.72
                }
            }
            cleanedAmount.contains("₾") -> {
                // Remove GEL symbol and add to total sum
                val value = cleanedAmount.replace("₾", "").toDoubleOrNull()
                if (value != null) {
                    totalSum += value
                }
            }
        }
    }



    fun getTotalSum(): Double {
        return (totalSum * 100).roundToInt() / 100.0
    }
}