package kr.or.ddit.basetech.designpattern.example;

public class AdapterPlug implements PluggableCN{
	private final AdapterPlug plug;

	public AdapterPlug(AdapterPlug plug) {
		super();
		this.plug = plug;
	}

	@Override
	public void receiveElectricWithThreeLeg() {
		plug.receiveElectricWithThreeLeg();
	}
}