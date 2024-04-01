package kr.or.ddit.fancytree;

import kr.or.ddit.vo.ProdVO;

public class ProdVOFancytreeNode extends AbstractFancytreeNode<ProdVO> {

	public ProdVOFancytreeNode(ProdVO source) {
		super(source);
	}

	@Override
	public String getKey() {
		return getSource().getProdId();
	}

	@Override
	public String getTitle() {
		return getSource().getProdName();
	}

	@Override
	public boolean isExpanded() {
		return false;
	}

	@Override
	public boolean isFolder() {
		return false;
	}

	@Override
	public boolean isLazy() {
		return false;
	}

}
