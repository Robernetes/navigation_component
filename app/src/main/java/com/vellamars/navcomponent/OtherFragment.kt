package com.vellamars.navcomponent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.vellamars.navcomponent.databinding.FragmentOtherBinding
import kotlinx.coroutines.launch


class OtherFragment : Fragment(), AdaptadorListener {

    private var _binding: FragmentOtherBinding? = null
    private val binding get() = _binding!!
    private var myInfo: Any? = null

    private lateinit var recyclerView: RecyclerView

    var listMedicine: MutableList<Medicine> = mutableListOf()
    lateinit var adaptador: AdaptadorMedicine
    lateinit var room:DBMedicine
    lateinit var medicine: Medicine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOtherBinding.inflate(inflater, container, false)



        //binding.recycleViewItems.layoutManager=LinearLayoutManager(requireContext())
        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.recycleViewItems
        recyclerView.layoutManager = layoutManager
        adaptador = AdaptadorMedicine(listMedicine, this@OtherFragment)
        recyclerView.adapter = adaptador



        room = Room.databaseBuilder(requireContext(), DBMedicine::class.java, "dbMedicine").build()



        getMedicine(room)


        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val layoutManager = LinearLayoutManager(context)
//        recyclerView = binding.recycleViewItems
//        recyclerView.layoutManager = layoutManager
//        adaptador = AdaptadorMedicine(listMedicine, this@OtherFragment)
//        recyclerView.adapter = adaptador

        //val args = OtherFragmentDirections.fromBundle(requireArguments())
//        val args = OtherFragmentArgs.fromBundle(requireArguments())
//        val datos = args.datos

//        val args = this.arguments
//        val data = args?.get("content")
//        myInfo = data
//        binding.textView4.text = myInfo.toString()

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDeleteItemClick(medicine: Medicine) {
        lifecycleScope.launch {
            room.daoMedicine().delMedicine(medicine.UUID)
            adaptador.notifyDataSetChanged()
            getMedicine(room)
        }
    }

    fun getMedicine(room: DBMedicine){
        lifecycleScope.launch {
            listMedicine = room.daoMedicine().getMedicines()
            adaptador = AdaptadorMedicine(listMedicine, this@OtherFragment)
            binding.recycleViewItems.adapter = adaptador
        }
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

    }




}