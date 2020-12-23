using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Random = UnityEngine.Random;

public class Cleaner : MonoBehaviour
{
    private BoxCollider2D boxCollider;
    private Rigidbody2D rb2D;
    RaycastHit2D hit;
    public LayerMask blockingLayer;
    private float inverseMoveTime;
    public float moveTime = 0.1f;
    int DirX;
    int DirY;
    int randomNumber;
    public int PlayerTimeReduction;
    public int velocidad;
    public string[] movements = { "right", "up", "left", "down" };
    public int pos = 0;
    public int damage;


     // Start is called before the first frame update
    void Start()
    {
        boxCollider = GetComponent<BoxCollider2D>();
        rb2D = GetComponent<Rigidbody2D>();
        GameManager.instance.AddCleanerToList(this);

        //Ponemos las direcciones cuando empieza
        DirX = 1;
        DirY = 0;//No nos moveremos en eje vertical

        inverseMoveTime = 1f / moveTime;
        velocidad = Random.Range(15,25); // velocidad del personaje
        PlayerTimeReduction = 20; // definicion del timepo que hace perder la cleaner
        damage = 1; // tiempo que nos saca si xocamos con ella o ella con nosotros
    }
 
    
    public void MoveCleaner(  )
       
    {
        /*if (pos == movements.Length)
        {
            pos = 0;
        }
        string direction = movements[pos];
        if (direction == "right")
        {
            DirX = 1;
            DirY = 0;
        }
        else if (direction == "up")
        {
            DirX = 0;
            DirY = 1;
        }
        else if (direction == "left")
        {
            DirX = -1;
            DirY = 0;
        }
        else
        {
            DirX = 0;
            DirY = -1;
        }*/
        StartCoroutine(Movement(new Vector3(DirX, DirY, 0f)));


        
    }

    protected IEnumerator Movement(Vector3 inputplayer)
    {
        rb2D.MovePosition(GetComponent<Transform>().position + inputplayer * velocidad * Time.deltaTime);
        yield return null;
    }
    void OnCollisionEnter2D(Collision2D collision)
    {
        DirX = DirX*-1;
    }



}
