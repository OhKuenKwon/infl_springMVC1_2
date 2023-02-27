package infl.spmvc1.spMvcEx1.mvc4.dao;

import infl.spmvc1.spMvcEx1.mvc4.vo.MemberVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, MemberVO> store = new HashMap<>();
    private static long seq = 0L;

    //싱글톤 객체 생성
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }

    //기본 생성자 막기
    private MemberRepository(){

    }

    public MemberVO save(MemberVO member){
        member.setId(++seq);
        store.put(member.getId(), member);
        return member;
    }

    public MemberVO findById(Long id){
        return store.get(id);
    }

    public List<MemberVO> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
