using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour
{
    public BoardManager boardScript;
    public static GameManager instance = null;
    public int zone = 3;
    public int playerTime = 120;
    public float turnDelay = 0.1f;
    private List<Cleaner> cleaners;
    private bool enemiesMoving;


    // Start is called before the first frame update
    void Awake()
    {
        if (instance == null)
            instance = this;
        else if (instance != this)
            Destroy(gameObject);
        DontDestroyOnLoad(gameObject);

        cleaners = new List<Cleaner>();

        boardScript = GetComponent<BoardManager>();
        InitGame();


    }

    void InitGame()
    {
        cleaners.Clear();
        boardScript.SetUpScene();
    }

    IEnumerator MoveEnemies()
    {
        enemiesMoving = true; //S'estan movent enemics
        yield return new WaitForSeconds(turnDelay);//Fem un delay
        if (cleaners.Count == 0)
        {
            yield return new WaitForSeconds(turnDelay);
        }
        for (int i = 0; i < cleaners.Count; i++)
        {
            cleaners[i].MoveCleaner();
            yield return new WaitForSeconds(cleaners[i].moveTime);
        }
        enemiesMoving = false;


    }
    public void AddCleanerToList(Cleaner script)
    {
        cleaners.Add(script);
    }

    // Update is called once per frame
    void Update()
    {
        StartCoroutine(MoveEnemies());
    }
}
