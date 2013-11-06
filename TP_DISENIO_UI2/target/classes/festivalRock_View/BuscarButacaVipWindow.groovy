package festivalRock_View

class BuscarButacaVipWindow extends BuscarButacaWindow{

	BuscarButacaVipWindow(owner){
		super(owner)
	}
	
	@Override
	def search(){
		modelObject.searchVip()
	}
	
	@Override
	def esVip() {
		return true
	}
}
