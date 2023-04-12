package rpgDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexaobd.conexaobd;
import models.personagem;

public class rpgDAO {

	
	public void save (personagem personagem) {
		String sql = "INSERT INTO personagem (nome,level,ataque,defesa,datacadastro) values (?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = conexaobd.createConnectionToMySQL();
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, personagem.getNome());
			pstm.setInt(2, personagem.getLevel());
			pstm.setInt(3, personagem.getAtaque());
			pstm.setInt(4, personagem.getDefesa());
			pstm.setDate(5, new Date(personagem.getDataCadastro().getTime()));
			
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}


public List<personagem> getPersonagem(){

	
	String sql = "SELECT*FROM personagem";
	
	List<personagem> personagens = new ArrayList<personagem>();
	 
	Connection conn = null;
	
	PreparedStatement pstm = null;
	ResultSet rset = null;
	
	try {
		conn = conexaobd.createConnectionToMySQL();
		
		pstm = conn.prepareStatement(sql);
		
		rset = pstm.executeQuery();
				
		while(rset.next()){
			personagem personagem = new personagem();
			personagem.setId(rset.getInt("id"));
			personagem.setNome(rset.getString("nome"));
			personagem.setLevel(rset.getInt("level"));
			personagem.setAtaque(rset.getInt("ataque"));
			personagem.setDefesa(rset.getInt("defesa"));
			personagem.setDataCadastro(rset.getDate("datacadastro"));
					
			personagens.add(personagem);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		try {
			if(rset!=null) {
				rset.close();
			}
			if(pstm!=null) {
				pstm.close();
		}
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return personagens;
 }

	public String batalhar(int idPersonagem1, int idPersonagem2) {
    String resultado = "";

    // buscar os personagens no banco de dados
    personagem personagem1 = null, personagem2 = null;
    String sql = "SELECT * FROM personagem WHERE id = ?";

    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    try {
        con = conexaobd.createConnectionToMySQL();

        // buscar o primeiro personagem
        pstm = con.prepareStatement(sql);
        pstm.setInt(1, idPersonagem1);
        rs = pstm.executeQuery();
        if (rs.next()) {
            personagem1 = new personagem();
            personagem1.setId(rs.getInt("id"));
            personagem1.setNome(rs.getString("nome"));
            personagem1.setLevel(rs.getInt("level"));
            personagem1.setAtaque(rs.getInt("ataque"));
            personagem1.setDefesa(rs.getInt("defesa"));
            personagem1.setDataCadastro(rs.getDate("datacadastro"));
        }

        // buscar o segundo personagem
        pstm = con.prepareStatement(sql);
        pstm.setInt(1, idPersonagem2);
        rs = pstm.executeQuery();
        if (rs.next()) {
            personagem2 = new personagem();
            personagem2.setId(rs.getInt("id"));
            personagem2.setNome(rs.getString("nome"));
            personagem2.setLevel(rs.getInt("level"));
            personagem2.setAtaque(rs.getInt("ataque"));
            personagem2.setDefesa(rs.getInt("defesa"));
            personagem2.setDataCadastro(rs.getDate("datacadastro"));
        }

        // realizar a batalha
        
        //Se o ataque for maior que a defesa, o com maior ataque ganha.
        //Se o p1 ataque - p2 defesa for igual ao p2ataque- p2defesa a batalha será empate.
        //Se a p1 defesa > p2 ataque, a p2 ganha.
        
        if (personagem1 == null || personagem2 == null) {
            resultado = "Não foi possível encontrar os personagens informados.";
        } else {
            int ataquePersonagem1 = personagem1.getAtaque() - personagem2.getDefesa();
            int ataquePersonagem2 = personagem2.getAtaque() - personagem1.getDefesa();

            if (ataquePersonagem1 > ataquePersonagem2) {
                resultado = "Personagem " + personagem1.getNome() + " venceu a batalha!";

            } else if (ataquePersonagem1 < ataquePersonagem2) {
                resultado = "Personagem " + personagem2.getNome() + " venceu a batalha!";

            } else {
                resultado = "A batalha terminou em empate!";
            }

        }
    } catch (Exception e) {
        e.printStackTrace();
        resultado = "Ocorreu um erro ao realizar a batalha.";
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return resultado;
}





}