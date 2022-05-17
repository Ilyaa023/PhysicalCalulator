package eco.point.physycalcalulator

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eco.point.physycalcalulator.databinding.TableRowItemBinding

class TableAdapter(var list: ArrayList<TableItem>): RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    inner class TableViewHolder(val item: View): RecyclerView.ViewHolder(item){
        fun bind (position: Int){
            val binding = TableRowItemBinding.bind(item)
            binding.aOutField.text = list[position].aOut.toString()
            binding.sqrAOutField.text = list[position].sqrAOut.toString()
            binding.inputField.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (binding.inputField.text.matches(Regex("(.+)")))
                        list[position].input = binding.inputField.text.toString().toDouble()
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.table_row_item, parent, false)
        return TableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size
}
