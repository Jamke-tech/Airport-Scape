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
        velocidad = 15;
    }
 
    
    public void MoveCleaner(  )
       
    {
        Vector2 start = transform.position;
        Vector2 end = start + new Vector2(DirX, DirY);
        boxCollider.enabled = false;
        hit = Physics2D.Linecast(start, end, blockingLayer);
        boxCollider.enabled = true;

        if (hit.transform == null)//mirem si hem xocat
        {
            StartCoroutine(Movement(new Vector3(DirX, DirY, 0f)));

        }
        else
        {
            if (pos == movements.Length)
            {
                pos = 0;
            }
            string direction = movements[pos];
            if (direction == "right")
            {
                DirX = 1;
                DirY = 0;
            }
            else if(direction =="up")
            {
                DirX = 0;
                DirY = 1;
            }
            else if(direction=="left")
            {
                DirX = -1;
                DirY = 0;
            }
            else
            {
                DirX = 0;
                DirY = -1;
            }
            pos = Random.Range(0, 4);
        }
        
    }
    protected IEnumerator Movement(Vector3 inputplayer)
    {
        rb2D.MovePosition(GetComponent<Transform>().position + inputplayer * velocidad * Time.deltaTime);
        yield return null;
    }

    protected IEnumerator SmoothMovement(Vector3 end)
    {
        float sqrRemainingDistance = (transform.position - end).sqrMagnitude;
        while (sqrRemainingDistance > float.Epsilon)
        {
            Vector3 newPosition = Vector3.MoveTowards(rb2D.position, end, inverseMoveTime * Time.deltaTime);
            rb2D.MovePosition(newPosition);
            sqrRemainingDistance = (transform.position - end).sqrMagnitude;
            yield return null; // ens esperem un frame per fer la modificacio de la posicio
        }
    }

    void OnCollisionEnter2D (Collision2D collision)
    {
        if(collision.gameObject.tag == "PlayerController")
        {
            PlayerController player = collision.gameObject.GetComponent<PlayerController>();
            player.LooseTime(PlayerTimeReduction);
        }
    }


}
