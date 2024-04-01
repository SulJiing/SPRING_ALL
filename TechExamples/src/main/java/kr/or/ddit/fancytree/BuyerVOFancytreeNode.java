package kr.or.ddit.fancytree;

import java.util.List;
import java.util.stream.Collectors;

import kr.or.ddit.vo.BuyerVO;

public class BuyerVOFancytreeNode extends AbstractFancytreeNode<BuyerVO> {

	public BuyerVOFancytreeNode(BuyerVO source) {
		super(source);
	}

	@Override
	public String getKey() {
		return getSource().getBuyerId();
	}

	@Override
	public String getTitle() {
		return getSource().getBuyerName();
	}

	@Override
	public boolean isExpanded() {
		return false;
	}

	@Override
	public boolean isFolder() {
		return true;
	}
	
	@Override
	public List<AbstractFancytreeNode<?>> getChildren() {
		return getSource().getProdList().stream()
					.map(ProdVOFancytreeNode::new)
					.collect(Collectors.toList());
	}
}
