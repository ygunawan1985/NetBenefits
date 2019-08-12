package com.example.netbenefitsapp.view.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.stockresponse.StockResponse
import com.example.netbenefitsapp.view.adapters.StockListAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var stockList: ArrayList<StockResponse> = ArrayList()
    private lateinit var stockResponseList : List<StockResponse>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stockList = it.getParcelableArrayList(ARG_PARAM1)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val layoutManager = LinearLayoutManager(view.context)
//        rvCelebrities = view.findViewById(R.id.rvCelebrities)
//        rvCelebrities.setLayoutManager(layoutManager)
//        val celebritiesListAdapter = CelebritiesListAdapter(celebrities, this)
//        rvCelebrities.setAdapter(celebritiesListAdapter)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val stockListAdapter = StockListAdapter(stockList)
        recyclerView.adapter = stockListAdapter
    }

    fun setStocks(stockResponseList: List<StockResponse>) {
        this.stockResponseList = stockResponseList
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(stockResponseList: ArrayList<StockResponse>) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, stockResponseList)
                }
            }
    }
}
