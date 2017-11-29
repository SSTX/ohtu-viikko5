package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    private String evenScore() {
        switch (m_score1) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    public String scoreGreaterThanThree() {
        String score = "";
        int scoreDiff = m_score1-m_score2;
        if (scoreDiff==1) score ="Advantage player1";
        else if (scoreDiff ==-1) score ="Advantage player2";
        else if (scoreDiff>=2) score = "Win for player1";
        else score ="Win for player2";
        return score;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (m_score1==m_score2) {
            return evenScore();
        } else if (m_score1>=4 || m_score2>=4) {
            return scoreGreaterThanThree();
        } else {
            for (int i=1; i<3; i++) {
                if (i==1) tempScore = m_score1;
                else { score+="-"; tempScore = m_score2;}
                switch(tempScore) {
                    case 0:
                        score+="Love";
                        break;
                    case 1:
                        score+="Fifteen";
                        break;
                    case 2:
                        score+="Thirty";
                        break;
                    case 3:
                        score+="Forty";
                        break;
                }
            }
        }
        return score;
    }
}
