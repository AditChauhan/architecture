package com.example.beloo.architecture.presentation.producers

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beloo.architecture.R
import com.example.beloo.architecture.data.model.producer.Producer
import com.example.beloo.architecture.presentation.PresentationActivity
import com.example.beloo.architecture.presentation.util.recyclerView.FactoryAdapter
import com.example.beloo.architecture.presentation.util.recyclerView.RecyclerViewPaginationListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/** Note: i decided to use activity to simplify things,
 * but commonly particular screen is based on fragments
 * and activity is responsible only for flow*/
class ProducersActivity : PresentationActivity(), ProducersContract.View {

    @Inject
    lateinit var presenter: ProducersContract.Presenter

    private var viewHolderFactory = ProducerViewHolderFactory()

    private val itemAdapter: FactoryAdapter<Producer> =
        FactoryAdapter(viewHolderFactory, diffUtil = viewHolderFactory.createDiffUtilCallback())

    private lateinit var paginationListener: RecyclerViewPaginationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val manager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        paginationListener = RecyclerViewPaginationListener(
            layoutManager = manager,
            onLoadNext = {
                presenter.onLoadNextPage()
            },
            offsetItemsCount = 5
        )

        rvList.apply {
            layoutManager = manager
            addOnScrollListener(paginationListener)
            adapter = itemAdapter
        }
    }

    override fun setItemsLoadedCompletely() {
        paginationListener.setLoadingFinished()
    }

    override fun setData(producers: List<Producer>) {
        paginationListener.totalItemsCount = producers.size
        vEmpty.visibility = if (producers.isEmpty()) View.VISIBLE else View.GONE
        itemAdapter.submitList(producers)
    }

    override fun hideProgress() {
        paginationListener.setIsLoadingNextPage(false)
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

}
