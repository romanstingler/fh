package at.fhkaernten;

public class Cupboard {
	private Cap ccap;
	private Gloves cgloves;
	private Scarf cscarf;

	public Cupboard(Cap cap, Gloves gloves, Scarf scarf) {
		this.putCcap(cap);
		this.putCgloves(gloves);
		this.putCscarf(scarf);
	}

	public synchronized Cap getCcap() throws InterruptedException {
		while (ccap == null)
			wait();
		Cap cap = ccap;
		ccap = null;
		return cap;
	}

	public synchronized void putCcap(Cap ccap) {
		this.ccap = ccap;
		notifyAll();
	}

	public synchronized Gloves getCgloves() throws InterruptedException {
		while (cgloves == null)
			wait();
		Gloves gloves = cgloves;
		cgloves = null;
		return gloves;
	}

	public synchronized void putCgloves(Gloves cgloves) {
		this.cgloves = cgloves;
		notifyAll();
	}

	public synchronized Scarf getCscarf() throws InterruptedException {
		while (cscarf == null)
			wait();
		Scarf scarf = cscarf;
		cscarf = null;
		return scarf;
	}

	public synchronized void putCscarf(Scarf cscarf) {
		this.cscarf = cscarf;
		notifyAll();

	}

}
