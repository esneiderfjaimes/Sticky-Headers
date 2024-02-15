package co.nei.stickyheader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.nei.stickyheader.databinding.FragmentHomeListBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeListBinding? = null
    private val binding get() = _binding
    private var stickyHeaderItemDecorator: StickyHeaderItemDecorator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initStickyItemDecorator()
    }

    private fun initStickyItemDecorator() {
        stickyHeaderItemDecorator?.clearReferences()
        stickyHeaderItemDecorator = null

        stickyHeaderItemDecorator = StickyHeaderItemDecorator()
        binding?.recyclerView?.let {
            val adapter = it.adapter
            if (adapter is ListRecyclerAdapter) {
                stickyHeaderItemDecorator?.attachRecyclerView(
                    adapter,
                    it,
                    adapter
                )
            }
        }
    }

    private fun initAdapter() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListRecyclerAdapter(getDummyItems()) {
                // (activity as? MainActivity)?.launchDetailPage(it)
            }
        }
    }

    private fun getDummyItems(): List<ItemModel> {
        return arrayListOf(
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(isHeader = true),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
            ItemModel(),
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

data class ItemModel(val isHeader: Boolean = false)
